import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import '../CssComp/warehouseform.css';

export default function WarehouseForm() {
  const [formData, setFormData] = useState({
    warehouseName: '',
    price: '',
    capacity: '',
    isAvailable: false,
    addressLine1: '',
    addressLine2: '',
    city: '',
    postalCode: '',
    owner:''
  });
  
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = jwtDecode(token);
      const username = decodedToken.username;
      setFormData(prevState => ({ ...prevState}));
    } else {
      console.error('Token not found in localStorage');
    }
  }, []);

  const handleChange = (e) => {
    const { name, type, checked } = e.target;
    const newValue = type === 'checkbox' ? checked : e.target.value;
    setFormData(prevState => ({
      ...prevState,
      [name]: newValue
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const token = localStorage.getItem('token');
      console.log(formData.owner)
      const response = await fetch('http://localhost:8080/warehouse/adddata', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer'+ ' '+ `${token}`,
        },
        body: JSON.stringify({
          ...formData
         
        })
      });

      if (!response.ok) {
        throw new Error('Error adding warehouse');
      }

      console.log('Warehouse added successfully');
      // Reset form fields
      setFormData({
        warehouseName: '',
        price: '',
        capacity: '',
        isAvailable: false,
        addressLine1: '',
        addressLine2: '',
        city: '',
        postalCode: '',
        owner:''
      });
      setError(null); // Reset error state
      navigate('/');
    } catch (error) {
      console.error('Error adding warehouse:', error);
      setError(error.message || 'Network Error');
    }
  };

  return (
    <div className="warehouseform">
      <h1>Warehouse form</h1>
      
    <form onSubmit={handleSubmit}>
      <label htmlFor="warehouseName">Warehouse Name:</label>
      <input type="text" id="warehouseName" name="warehouseName" value={formData.warehouseName} onChange={handleChange} required />

      <label htmlFor="price">Price:</label>
      <input type="number" id="price" name="price" value={formData.price} onChange={handleChange} required />

      <label htmlFor="capacity">Capacity:</label>
      <input type="number" id="capacity" name="capacity" value={formData.capacity} onChange={handleChange} required />

      <label htmlFor="isAvailable">Is Available:</label>
      <input type="checkbox" id="isAvailable" name="isAvailable" checked={formData.isAvailable} onChange={handleChange} />

      <label htmlFor="addressLine1">Address Line 1:</label>
      <input type="text" id="addressLine1" name="addressLine1" value={formData.addressLine1} onChange={handleChange} required />

      <label htmlFor="addressLine2">Address Line 2:</label>
      <input type="text" id="addressLine2" name="addressLine2" value={formData.addressLine2} onChange={handleChange} />

      <label htmlFor="city">City:</label>
      <input type="text" id="city" name="city" value={formData.city} onChange={handleChange} required />

      <label htmlFor="postalCode">Postal Code:</label>
      <input type="number" id="postalCode" name="postalCode" value={formData.postalCode} onChange={handleChange} required />
      <label htmlFor="ownerId">Owner:</label>
      <input type="number" id="owner" name="owner" value={formData.owner} onChange={handleChange} required />
      <button type="submit">Submit</button>
      {error && <div className="error-message">{error}</div>}
    </form>
    </div>
  );
}
