import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import useMediaQuery from "@mui/material/useMediaQuery";
import { NavLink } from "react-router-dom";
import {
  toggleOff,
  toggleOn,
  setCurrentModule,
  setActiveModuleStyles,
} from "../../state/state";
import { menuItems } from "../../utils/menu-items/menuItems";
import { MdMenu as Toggle } from "react-icons/md";
import { NavContainer } from "./Nav.styles";

const DesktopNav = () => {
  const dispatch = useDispatch();
  const isOpen = useSelector((state) => state.isOpen);
  const currentModule = useSelector((state) => state.currentModule);
  const productsTab = useSelector((state) => state.productsTab);
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const activeModuleStyles = useSelector((state) => state.activeModuleStyles);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");

  const tabMapping = {
    dashboard: "",
    products: `/${productsTab}`,
    transactions: `/${transactionsTab}`,
    thirdparty: "",
    // add more modules and their corresponding tabs as needed
  };

  const handleToggle = () =>
    isOpen ? dispatch(toggleOff()) : dispatch(toggleOn());

  const handleClick = (module) => {
    dispatch(setCurrentModule(module));
  };

  useEffect(() => {
    dispatch(
      setActiveModuleStyles(
        `flex gap-2 lg:w-full px-5 py-3 rounded-md transition ease-in-out delay-50 bg-[#112D4E] text-[#F9F7F7]`
      )
    );
  }, [currentModule]);

  return (
    <NavContainer isNotAPhone={isNotAPhone} isOpen={isOpen} className="sidebar">
      <Toggle className="text-2xl cursor-pointer" onClick={handleToggle} />
      <div className="nav-links flex flex-col gap-5 items-center pt-10 h-full">
        {menuItems.map((item) => (
          <NavLink
            to={`/${item.name}${tabMapping[item.name]}`}
            className={
              currentModule == item.name
                ? activeModuleStyles
                : `flex gap-2 lg:w-full px-5 py-3 rounded-md text-[#000] transition ease-in-out delay-50 hover:bg-[#112D4E] hover:text-[#F9F7F7]`
            }
            key={item.name}
            onClick={() => handleClick(item.name)}
          >
            <div className="text-[1.5rem]">{item.icon}</div>
            {isOpen && (
              <div className="text">
                <p className="text-[0.9rem]">{item.label}</p>
              </div>
            )}
          </NavLink>
        ))}
      </div>
    </NavContainer>
  );
};

export default DesktopNav;
