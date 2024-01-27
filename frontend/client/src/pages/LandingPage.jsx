import React from "react";
import RegisterForm from "../components/register.jsx";
import LoginForm from "../components/login.jsx";

const LandingPage = () => {
      return (
            <div>
                  <h1>Welcome to OpenSourceBooks!</h1>
                  <nav></nav>

                  <main role="main" className="inner cover">
                        <h2>Sign in to continue browsing our library!</h2>
                        <LoginForm />
                        <br />
                        <h3>If you're not already a member, consider signing up to access our catelogue of Open Source eBooks and Audiobooks!</h3>
                        <RegisterForm />
                  </main>
            </div>
      );
};

export default LandingPage;