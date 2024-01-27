import { NavLink } from 'react-router-dom';
import { ReactComponent as Brand } from "../OpenBooksLogo.png";
import './navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="container">
        <div className="logo">
          <Brand />
        </div>
        <div className="nav-elements">
          <ul>
            <li>
              <NavLink to="/">Home</NavLink>
            </li>
            <li>
              <NavLink to="/contact-us">Contact Us!</NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  )
}

export default Navbar