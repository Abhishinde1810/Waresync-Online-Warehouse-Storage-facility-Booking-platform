import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../CssComp/register.css';
export default function Signupcus() {
    const [values, setValues] = useState({
        name: '',
        mobileNo: '',
        email: '',
        password: '',
        addressLine1: '',
        addressLine2: '',
        city: '',
        postalCode: ''
    });

    const navigate = useNavigate();

    const handleInput = (event) => {
        setValues((prev) => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(values)
        axios.post('http://localhost:8080/customer/register',values)
            .then((res) => {
                console.log(res);
                navigate('/');
            })
            .catch((err) => console.log(err));
    };

    return (
        <div className="container">
            <div className='form-container'><b>Registration</b>
            <form className="row g-3 needs-validation" noValidate onSubmit={handleSubmit}>
                <div className="col-md-4">
                    <label htmlFor="validationCustom01" className="form-label">
                        Customer Name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom01"
                        required
                        onChange={handleInput}
                        name="name"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom02" className="form-label">
                        Mobile No
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom02"
                        required
                        onChange={handleInput}
                        name="mobileNo"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom03" className="form-label">
                        Email
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom03"
                        required
                        onChange={handleInput}
                        name="email"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustomUsername" className="form-label">
                        Password
                    </label>
                    <input
                        type="password"
                        className="form-control"
                        id="validationCustomUsername"
                        aria-describedby="inputGroupPrepend"
                        required
                        onChange={handleInput}
                        name="password"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom04" className="form-label">
                        Address Line 1
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom04"
                        required
                        onChange={handleInput}
                        name="addressLine1"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom05" className="form-label">
                        Address Line 2
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom05"
                        required
                        onChange={handleInput}
                        name="addressLine2"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom06" className="form-label">
                        City
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom06"
                        required
                        onChange={handleInput}
                        name="city"
                    />
                </div>
                <div className="col-md-4">
                    <label htmlFor="validationCustom07" className="form-label">
                        Postal Code
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="validationCustom07"
                        required
                        onChange={handleInput}
                        name="postalCode"
                    />
                </div>
                <div className="col-12">
                    <button className="btn btn-primary" type="submit">
                        Submit form
                    </button>
                </div>
            </form>
        </div>
        </div>
    );
}
