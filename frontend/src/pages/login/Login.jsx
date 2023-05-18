/* eslint-disable react/no-unescaped-entities */
import { React, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { signIn } from "../../auth/firebase";
import { setUser } from "../../state/state";

const defaultFormFields = {
  email: "",
  password: "",
};

const Login = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { email, password } = formFields;
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = await signIn(email, password);
    dispatch(setUser({ user }));
    navigate("/");
    resetFormFields();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormFields({ ...formFields, [name]: value });
  };

  const resetFormFields = () => {
    setFormFields(defaultFormFields);
  };

  return (
    <div className="h-screen w-full flex flex-col items-center justify-center gap-10 bg-[#DBE2EF]">
      <div className="flex flex-col items-center justify-center">
        <h1 className="text-3xl pb-3">pharma.pa</h1>
        <h2 className="text-lg">Log into your account</h2>
      </div>
      <div className="flex flex-col items-center gap-[2rem] text-sm">
        <form className="w-full grid gap-3" onSubmit={handleSubmit}>
          <input
            className="bg-white rounded-md px-4 py-3"
            type="text"
            placeholder="Email"
            onChange={handleChange}
            name="email"
            value={email}
          />
          <input
            className="bg-white rounded-md px-4 py-3"
            type="password"
            placeholder="Password"
            onChange={handleChange}
            name="password"
            value={password}
          />
          <button
            type="submit"
            className="w-full py-3 px-4 rounded-md bg-[#112D4E] text-white text-sm hover:bg-[#2E63A0]"
          >
            Sign in
          </button>
        </form>
        <p>
          Don't have an account?{" "}
          <Link to="/register" className="hover:underline">
            Register here.
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
