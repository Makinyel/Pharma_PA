import { useEffect } from "react";
import { NavLink } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { signOutUser } from "../../auth/firebase";
import { menuItems } from "../../utils/menu-items/menuItems";
import {
  toggleOff,
  toggleOn,
  setCurrentModule,
  setActiveModuleStyles,
} from "../../state/state";

import { MdMenu as Toggle } from "react-icons/md";
import { NavContainer, NavLinkContainer, TitleContainer } from "./Nav.styles";
import { useMediaQuery } from "@mui/material";

const MobileNav = () => {
  const dispatch = useDispatch();
  const isOpen = useSelector((state) => state.isOpen);
  const currentModule = useSelector((state) => state.currentModule);
  const productsTab = useSelector((state) => state.productsTab);
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const activeModuleStyles = useSelector((state) => state.activeModuleStyles);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const user = useSelector((state) => state.user);

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
        `flex gap-2 items-center w-full lg:w-[70%] px-5 py-3 rounded-md transition ease-in-out delay-50 bg-[#112D4E] text-[#F9F7F7]`
      )
    );
  }, [currentModule]);

  const handleSignOut = () => {
    dispatch(signOutUser());
    window.location.href = "/";
  };

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
                  : `flex gap-2 items-center w-full lg:w-[70%] px-5 py-3 rounded-md text-[#000] transition ease-in-out delay-50 hover:bg-[#112D4E] hover:text-[#F9F7F7]`
              }
              key={item.name}
              onClick={() => handleClick(item.name)}
            >
              {item.icon}
              <p className="text-[0.9rem]">{item.label}</p>
            </NavLink>
          ))}
          <div className="flex justify-between gap-5 items-center text-sm">
            <span>{user.email}</span>
            <button
              className="hover:bg-[#112D4E] hover:text-white rounded-md transition-all py-1 px-2"
              onClick={handleSignOut}
            >
              Cerrar sesión
            </button>
          </div>
        </NavLinkContainer>
      )}
    </NavContainer>
  );
};

export default MobileNav;
