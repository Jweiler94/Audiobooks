import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Root from "./routes/root";
import ErrorPage from "./error";
import Home from "./pages/Home.jsx";
import LandingPage from "./pages/LandingPage.jsx";
import Contact from "./pages/Contact.jsx";
import { AuthUserProvider } from "./AuthUser";
import AudiobookLibrary from './pages/AudiobookLibrary.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      { index: true, element: <LandingPage /> },
      { path: "home", element: <Home /> },
      { path: "contact-us", element :<Contact /> },
      { path: "audiobooks", element: <AudiobookLibrary /> }
    ],
    errorElement: <ErrorPage />,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <AuthUserProvider>
        <RouterProvider router={router} />
      </AuthUserProvider>
  </React.StrictMode>,
);
