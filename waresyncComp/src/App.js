import logo from './logo.svg';
import './App.css';
import Signup from './components/Signup';
import Warehouse  from './components/Warehouse';
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import Login from './components/Login';
import Home from './components/Home';
import CustomerHome from './components/CustomerHome';
import Update from './components/Update'
import Signupcus from './components/Signupcus';
import Book from './components/Book';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
      <Route path='/' element={<Home></Home>}/>
      <Route path='/cushome' element={<CustomerHome></CustomerHome>}/>
      <Route path="/signup" element={<Signup></Signup>}/>
      <Route path="/signupcus" element={<Signupcus></Signupcus>}/>
      <Route path="/update/:id" element={<Update />} />
      <Route path='/book' element={<Book></Book>}/>
      <Route path='/login' element={<Login></Login>}/>
      <Route path='/add' element={<Warehouse></Warehouse>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
