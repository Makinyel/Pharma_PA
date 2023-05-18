import { MdOutlineDashboard as DashboardIcon } from "react-icons/md";
import {
  GiMedicines as ProductosIcon,
  GiSellCard as MovimientosIcon,
} from "react-icons/gi";
import { BsPeopleFill as TercerosIcon } from "react-icons/bs";

export const menuItems = [
  {
    icon: <DashboardIcon className="text-[1.5rem]" />,
    label: "Dashboard",
    name: "dashboard",
  },
  {
    icon: <ProductosIcon className="text-[1.5rem]" />,
    label: "Product",
    name: "products",
  },
  {
    icon: <MovimientosIcon className="text-[1.5rem]" />,
    label: "Transactions",
    name: "transactions",
  },
  {
    icon: <TercerosIcon className="text-[1.5rem]" />,
    label: "Thirds",
    name: "thirdparty",
  },
];
