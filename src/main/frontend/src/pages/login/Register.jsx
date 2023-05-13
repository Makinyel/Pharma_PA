import { useState } from "react";
import { Link } from "react-router-dom";
import { createAccount } from "../../auth/firebase";

const defaultFormFields = {
  name: "",
  email: "",
  password: "",
  address: "",
  phone: "",
  role: "",
};

const Register = () => {
  const [formFields, setFormFields] = useState(defaultFormFields);
  const { name, email, password, role } = formFields;
  const userInformation = {
    displayName: name,
    email,
    password,
    role,
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      createAccount(userInformation);
      resetFormFields();
    } catch (error) {
      console.log(error);
    }
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
        <h2 className="text-xl">Create a new account</h2>
      </div>
      <div className="flex flex-col items-center gap-[2rem]">
        <form className="w-full" onSubmit={handleSubmit}>
          <div className="flex flex-col gap-5">
            <input
              className="bg-white rounded-md px-4 py-3"
              type="text"
              placeholder="Full name"
              onChange={handleChange}
              name="name"
              value={name}
            />
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
            <input
              className="bg-white rounded-md px-4 py-3"
              type="text"
              placeholder="Ubicacion"
              onChange={handleChange}
              name="ubicacion"
              value={ubicacion}
            />
            <select
              className="bg-white rounded-md px-4 py-3 text-sm text-gray-400"
              name="role"
              id="role"
              onChange={handleChange}
              value={role}
            >
              <option className="text-sm" value="">
                Role...
              </option>
              <option className="text-sm" value="GERCOM">
                Gerente de compra
              </option>
              <option className="text-sm" value="GERVEN">
                Gerente de venta
              </option>
              <option className="text-sm" value="ADMIN">
                Admin
              </option>
            </select>
          </div>
          <button
            type="submit"
            className="w-full mt-10 py-3 px-4 rounded-md bg-[#112D4E] text-white text-sm hover:bg-[#2E63A0]"
          >
            Create account
          </button>
        </form>
        <p className="text-xs">
          Already have an account?{" "}
          <Link to="/login" className="hover:underline">
            Log in here.
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Register;
