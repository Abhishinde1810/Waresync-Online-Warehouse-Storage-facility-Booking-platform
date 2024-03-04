import React, { useState,useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import '../CssComp/login.css'
export default function Login() {
  const [values, setValues] = useState({
    email: '',
    password: ''
  });
  const [role,setRole]=useState('');
  const navigate = useNavigate();

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value
    });
  };
  const handleRole=(event)=>{
    setRole(event.target.value);
  }
  useEffect(() => {
    const token = localStorage.getItem('token');
    const isLoggedIn = localStorage.getItem('isLoggedIn');
    
    if (token && isLoggedIn) { // Check if user is already logged in
      navigate('/'); // Redirect to home if user is already logged in
    }
  }, [navigate]);
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      let endpoint = '';
      if (role === 'owner') {
        endpoint = 'http://localhost:8080/owner/authowner';
      } else if (role === 'customer') {
        endpoint = 'http://localhost:8080/customer/login';
      }
  
      const response = await axios.post(endpoint, values, {
        headers: {
          'Content-Type': 'application/json',
        }
      });
      console.log(response);
      console.log(response.data.username)
      const { token } = response.data;
      if (token) {
        const decodedToken = jwtDecode(token);
        localStorage.setItem('token', token);
        localStorage.setItem('tokenExpiration', decodedToken.exp);
        localStorage.setItem('isLoggedIn', true);
        localStorage.setItem('username', response.data.username);
        if(role=="owner"){
          navigate('/');
        }else{
          navigate( '/cushome' );
        }
      } else {
        navigate('/login')
        alert('Wrong email and password');
      }
    } catch (error) {
      console.error('Axios request failed:', error);
      // Handle errors
    }
  };

  const registerPage = () => {
    if (role === 'owner') {
      navigate('/Signup');
    } else if (role === 'customer') {
      navigate('/Signupcus');
    }
  };

  return (
    <div className='first'>
    <div className="login-form">
      <h2>WARESYNC</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="email">Email:</label>
        <input type="email" id="email" name="email" required onChange={handleChange} value={values.email} />
        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required onChange={handleChange} value={values.password} />
        <div>
          <label htmlFor="role">Role:</label>
          <select id="role" name="role" value={values.role} onChange={handleRole}>
            <option value="customer">Customer</option>
            <option value="owner">Owner</option>
          </select>
        </div>
        <button type="submit">Login</button>
      </form>
      {role && (
          <p>Don't have an account? <a onClick={registerPage}>Register here</a></p>
        )}
    </div>
  </div>
  );
}
