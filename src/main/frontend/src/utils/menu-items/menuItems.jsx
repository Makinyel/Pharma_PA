import { MdOutlineDashboard as DashboardIcon } from "react-icons/md";
import { GiMedicines as ProductosIcon } from "react-icons/gi";
import { GiSellCard as MovimientosIcon } from "react-icons/gi";
import { BsPeopleFill as TercerosIcon } from "react-icons/bs";

export const menuItems = [
  {
    icon: <DashboardIcon className="text-[1.5rem]" />,
    label: "Dashboard",
    name: "dashboard",
  },
  {
    icon: <ProductosIcon className="text-[1.5rem]" />,
    label: "Productos",
    name: "productos",
  },
  {
    icon: <MovimientosIcon className="text-[1.5rem]" />,
    label: "Movimientos",
    name: "movimientos",
  },
  {
    icon: <TercerosIcon className="text-[1.5rem]" />,
    label: "Terceros",
    name: "terceros",
  },
];
