import React, { useState,useEffect } from 'react';

const Book = () => {
    const [bookingData, setBookingData] = useState({
        name: '',
        wareId: '',
        startDate: '',
        endDate: ''
    });
    useEffect(() => {
        const storedCustname = localStorage.getItem('username');
        const storedWareId = localStorage.getItem('wareId');

        if (storedWareId) {
            setBookingData({
                ...bookingData,
                 name: storedCustname,
                wareId: storedWareId
            });
        }
    }, []);
    const handleChange = (e) => {
        const { name, value } = e.target;
        setBookingData({ ...bookingData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // You can perform any validation or submission logic here
        console.log(bookingData);
        // Reset form after submission if needed
        setBookingData({
            name: '',
            wareId: '',
            startDate: '',
            endDate: ''
        });
    };

    return (
        <div className="booking-form-container">
            <h2 className="form-title">Booking Form</h2>
            <form onSubmit={handleSubmit} className="booking-form">
                <div className="form-group">
                    <label htmlFor="custId">Customer Name:</label>
                    <input
                        type="text"
                        id="custName"
                        name="custName"
                        value={bookingData.name}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="wareId">Warehouse ID:</label>
                    <input
                        type="text"
                        id="wareId"
                        name="wareId"
                        value={bookingData.wareId}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="startDate">Start Date:</label>
                    <input
                        type="date"
                        id="startDate"
                        name="startDate"
                        value={bookingData.startDate}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="endDate">End Date:</label>
                    <input
                        type="date"
                        id="endDate"
                        name="endDate"
                        value={bookingData.endDate}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <button type="submit" className="btn-submit">Go for Payment</button>
            </form>
        </div>
    );
};

export default Book;
