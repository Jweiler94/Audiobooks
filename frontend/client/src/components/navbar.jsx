import { NavLink } from 'react-router-dom';
import Logo from "./logo";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="container">
        <div className="logo">
          <Logo />
        </div>
        <div className="nav-elements">
          <ul>
            <li>
              <NavLink to="/">Home</NavLink>
            </li>
            <li>
              <NavLink to="/contact-us">Contact Us!</NavLink>
            </li>
            <li>
              <NavLink to="/audiobooks">Audiobook Library</NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;