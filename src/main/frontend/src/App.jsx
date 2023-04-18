import SideBar from "./components/SideBar";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Gestion from "./pages/gestion/Gestion";
import Cartera from "./pages/cartera/Cartera";
import Facturacion from "./pages/Facturacion";

const App = () => {
  return (
    <BrowserRouter>
      <SideBar>
        <Routes>
          <Route index path="/" element={<Dashboard />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/gestion/:tabItem" element={<Gestion />} />
          <Route path="/cartera/:tabItem" element={<Cartera />} />
          <Route path="/facturacion" element={<Facturacion />} />
        </Routes>
      </SideBar>
    </BrowserRouter>
  );
};

export default App;
