import { useState } from "react";
import { useDispatch } from "react-redux";
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

    await signIn(email, password).then((res) => {
      const { accessToken, displayName, email } = res;
      dispatch(setUser({ user: { accessToken, displayName, email } }));
      navigate("/");
    });
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
    <div className="h-screen w-full flex flex-col items-center justify-center gap-[5rem] bg-[#DBE2EF]">
      <div className="flex flex-col items-center justify-center gap-5">
        <h1 className="text-3xl">pharma.pa</h1>
        <h2 className="text-xl">Log into your account</h2>
      </div>
      <div className=" flex flex-col items-center gap-[2rem]">
        <form className="w-full">
          <div className="flex flex-col gap-5">
            <input
              className="bg-white rounded-md px-4 py-3"
              type="email"
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
          </div>
          <button
            type="submit"
            className="w-full mt-10 py-3 px-4 rounded-md bg-[#112D4E] text-white text-sm hover:bg-[#2E63A0]"
            onClick={handleSubmit}
          >
            Sign in
          </button>
        </form>
        <p className="text-xs">
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
