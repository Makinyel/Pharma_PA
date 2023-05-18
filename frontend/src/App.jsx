import { useSelector } from "react-redux";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SideBar from "./components/SideBar";
import Dashboard from "./pages/Dashboard";
import ThirdParty from "./pages/thirdparty/ThirdParty";
import Transactions from "./pages/transactions/Transactions";
import Products from "./pages/products/Products";
import Login from "./pages/login/Login";
import Register from "./pages/login/Register";

const App = () => {
  const user = useSelector((state) => state.user);
  return (
    <BrowserRouter>
      {user ? (
        <SideBar>
          <Routes>
            <Route index path="/" element={<Dashboard />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/products/:tabItem" element={<Products />} />
            <Route path="/transactions/:tabItem" element={<Transactions />} />
            <Route path="/thirdparty" element={<ThirdParty />} />
          </Routes>
        </SideBar>
      ) : (
        <Routes>
          <Route index path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default App;
