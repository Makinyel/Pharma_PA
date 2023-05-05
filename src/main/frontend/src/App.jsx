import { useSelector } from "react-redux";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import SideBar from "./components/SideBar";
import Dashboard from "./pages/Dashboard";
import Facturacion from "./pages/Facturacion";
import Cartera from "./pages/cartera/Cartera";
import Gestion from "./pages/gestion/Gestion";
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
            <Route path="/gestion/:tabItem" element={<Gestion />} />
            <Route path="/cartera/:tabItem" element={<Cartera />} />
            <Route path="/facturacion" element={<Facturacion />} />
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
