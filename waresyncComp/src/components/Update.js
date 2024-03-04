import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';
import '../CssComp/warehouseform.css';
import axios from 'axios';
export default function Update() {
  const [warehouseData, setWarehouseData] = useState({
    warehouseName: '',
    price: '',
    capacity: '',
    isAvailable: false,
    addressLine1: '',
    addressLine2: '',
    city: '',
    postalCode: '',
    ownerId: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { id } = useParams();
  const isUpdateForm = id !== undefined;

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (isUpdateForm) {
          const token = localStorage.getItem('token');
          const response = await axios.get(`http://localhost:8080/warehouse/ware/${id}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          });
          const responseData = response.data;
          setWarehouseData(responseData); // Directly set response data
        } else {
          setWarehouseData({ // Reset form fields for add form
            warehouseName: '',
            price: '',
            capacity: '',
            isAvailable: false,
            addressLine1: '',
            addressLine2: '',
            city: '',
            postalCode: '',
            ownerId: ''
          });
        }
        setError(null);
      } catch (error) {
        console.error('Error fetching or resetting warehouse data:', error);
        setError('Failed to fetch or reset warehouse data');
      }
    };
  
    fetchData();
  }, [id, isUpdateForm]);

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const token = localStorage.getItem('token');
      const response = await axios.put('http://localhost:8080/warehouse/update', warehouseData, {
        headers: {
          'Content-Type': 'application/json',
          // 'Authorization': `Bearer ${token}`
        }
      });
  
      if (response.status !== 200) { // Corrected condition for response status
        throw new Error('Failed to update warehouse data');
      }
  
      console.log('Warehouse data submitted successfully', response);
      setError(null);
      navigate('/');
    } catch (error) {
      console.error('Error submitting warehouse data:', error);
      setError(error.message || 'Network Error');
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const newValue = type === 'checkbox' ? checked : value;
    setWarehouseData(prevData => ({ ...prevData, [name]: newValue }));
  };


  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="warehouseName">Warehouse Name:</label>
      <input type="text" id="warehouseName" name="warehouseName" value={warehouseData.warehouseName} onChange={handleChange} required />

      <label htmlFor="price">Price:</label>
      <input type="text" id="price" name="price" value={warehouseData.price} onChange={handleChange} required />

      <label htmlFor="capacity">Capacity:</label>
      <input type="number" id="capacity" name="capacity" value={warehouseData.capacity} onChange={handleChange} required />

      <label htmlFor="isAvailable">Is Available:</label>
      <input type="checkbox" id="isAvailable" name="isAvailable" checked={warehouseData.isAvailable} onChange={handleChange} />

      <label htmlFor="addressLine1">Address Line 1:</label>
      <input type="text" id="addressLine1" name="addressLine1" value={warehouseData.addressLine1} onChange={handleChange} required />

      <label htmlFor="addressLine2">Address Line 2:</label>
      <input type="text" id="addressLine2" name="addressLine2" value={warehouseData.addressLine2} onChange={handleChange} />

      <label htmlFor="city">City:</label>
      <input type="text" id="city" name="city" value={warehouseData.city} onChange={handleChange} required />

      <label htmlFor="postalCode">Postal Code:</label>
      <input type="number" id="postalCode" name="postalCode" value={warehouseData.postalCode} onChange={handleChange} required />

      <label htmlFor="ownerId">Owner:</label>
      <input type="text" id="ownerId" name="ownerId" value={warehouseData.ownerId} onChange={handleChange} required />

      <button type="submit">Submit</button>
      {error && <div className="error">Error: {error}</div>}
    </form>
  );
}
