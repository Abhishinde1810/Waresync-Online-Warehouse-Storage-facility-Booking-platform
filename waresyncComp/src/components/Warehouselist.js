import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../CssComp/warehouselist.css';
import { useNavigate } from 'react-router-dom';

export default function WarehouseList() {
  const [warehouses, setWarehouses] = useState([]);
  const [filteredWarehouses, setFilteredWarehouses] = useState([]);
  const [error, setError] = useState('');
  const [searchName, setSearchName] = useState('');
  const [searchPrice, setSearchPrice] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchWarehouses = async () => {
      try {
        const token = localStorage.getItem('token');
        const username=localStorage.getItem('username')
        console.log(username)
        const response = await axios.get('http://localhost:8080/warehouse/getwarehouse', {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token,
          },
        });
        setWarehouses(response.data);
        setFilteredWarehouses(response.data);
      } catch (error) {
        console.error('Error fetching warehouses:', error);
        setError('Error fetching data');
      }
    };

    fetchWarehouses();
  }, []);
 
  useEffect(() => {
    const filterWarehouses = () => {
      let filtered = warehouses;
      if (searchName.trim() !== '') {
        filtered = filtered.filter(warehouse =>
          warehouse.warehouseName.toLowerCase().includes(searchName.toLowerCase())
        );
      }
      if (searchPrice.trim() !== '') {
        filtered = filtered.filter(warehouse => warehouse.price <= parseFloat(searchPrice));
      }
      setFilteredWarehouses(filtered);
    };

    filterWarehouses();
  }, [searchName, searchPrice, warehouses]);

  const handleUpdate = (warehouseId) => {
    navigate(`/update/${warehouseId}`);
  };

  const handleDelete = async (wareId) => {
    try {
      const token = localStorage.getItem('token');
      await axios.delete(`http://localhost:8080/warehouse/delete/${wareId}`, {
        headers: {
          'Authorization': 'Bearer ' + token
        },
      });
      setWarehouses(warehouses.filter(warehouse => warehouse.wareId !== wareId));
      setFilteredWarehouses(filteredWarehouses.filter(warehouse => warehouse.wareId !== wareId));
    } catch (error) {
      console.error('Error deleting warehouse:', error);
      setError('Error deleting warehouse');
    }
  };

  return (
    <div>
      <div>
        <h2>Search by Warehouse Name:</h2>
        <input
          type="text"
          placeholder="Enter warehouse name..."
          value={searchName}
          onChange={(e) => setSearchName(e.target.value)}
        />
      </div>
      <div>
        <h2>Search by Maximum Price:</h2>
        <input
          type="number"
          placeholder="Enter maximum price..."
          value={searchPrice}
          onChange={(e) => setSearchPrice(e.target.value)}
        />
      </div>
      <div className="warehouse-list">
        <h2>Warehouse List</h2>
        <div className="error">{error && <div>Error: {error}</div>}</div>
        <ul>
          {filteredWarehouses.map((warehouse) => (
            <li key={warehouse.wareId}>
              {/* Your warehouse details rendering code */}
              {/* ... */}
              <div class="details-row">
          <div class="detail-label">ID:</div>
          <div class="detail-value">{warehouse.wareId}</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Name:</div>
          <div class="detail-value">{warehouse.warehouseName}</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Price:</div>
          <div class="detail-value">{warehouse.price}</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Capacity:</div>
          <div class="detail-value">{warehouse.capacity} KGS</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Available:</div>
          <div class="detail-value">{warehouse.isAvailable ? 'false' : 'Available'}</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Address:</div>
          <div class="detail-value">{warehouse.addressLine1}, {warehouse.addressLine2}, {warehouse.city}, {warehouse.postalCode}</div>
        </div>
        <div class="details-row">
          <div class="detail-label">Owner:</div>
          <div class="detail-value">{warehouse.owner ? warehouse.owner.ownerName : 'Unknown'}</div>
        </div>
              <div className="buttons-row">
                <button className="update-button" onClick={() => handleUpdate(warehouse.wareId)}>Update</button>
                <button className="delete-button" onClick={() => handleDelete(warehouse.wareId)}>Delete</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
