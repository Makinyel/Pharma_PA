import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  toggleOff,
  toggleOn,
  setCurrentModule,
  setActiveModuleStyles,
} from "../../state/state";
import { NavLink } from "react-router-dom";
import { menuItems } from "../../utils/menuItems";

import { MdMenu as Toggle } from "react-icons/md";
import { NavContainer, NavLinkContainer, TitleContainer } from "./Nav.styles";
import { useMediaQuery } from "@mui/material";

const MobileNav = () => {
  const dispatch = useDispatch();
  const isOpen = useSelector((state) => state.isOpen);
  const currentModule = useSelector((state) => state.currentModule);
  const currentGestionTab = useSelector((state) => state.currentGestionTab);
  const currentCarteraTab = useSelector((state) => state.currentCarteraTab);
  const activeModuleStyles = useSelector((state) => state.activeModuleStyles);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");

  const tabMapping = {
    dashboard: "",
    gestion: `/${currentGestionTab}`,
    cartera: `/${currentCarteraTab}`,
    facturacion: "",
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
        `flex gap-2 items-center w-full lg:w-[70%] px-5 py-3 rounded-md transition ease-in-out delay-50 bg-dark text-light`
      )
    );
  }, [currentModule]);

  return (
    <NavContainer isNotAPhone={isNotAPhone} isOpen={isOpen}>
      <TitleContainer>
        <Toggle className="text-4xl cursor-pointer" onClick={handleToggle} />
        <NavLink
          to="/"
          className="title"
          onClick={() => handleClick("dashboard")}
        >
          pharma.pa
        </NavLink>
      </TitleContainer>
      {isOpen && (
        <NavLinkContainer isNotAPhone={isNotAPhone}>
          {menuItems.map((item) => (
            <NavLink
              to={`/${item.name}${tabMapping[item.name]}`}
              className={
                currentModule == item.name
                  ? activeModuleStyles
                  : `flex gap-2 items-center w-full lg:w-[70%] px-5 py-3 rounded-md text-[#000] transition ease-in-out delay-50 hover:bg-dark hover:text-light`
              }
              key={item.name}
              onClick={() => handleClick(item.name)}
            >
              {item.icon}
              <p className="text-[0.9rem]">{item.label}</p>
            </NavLink>
          ))}
        </NavLinkContainer>
      )}
    </NavContainer>
  );
};

export default MobileNav;
