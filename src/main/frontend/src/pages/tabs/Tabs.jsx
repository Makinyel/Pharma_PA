import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { setCurrentGestionTab, setCurrentCarteraTab } from "../../state/state";
import { Link, useNavigate } from "react-router-dom";
import useMediaQuery from "@mui/material/useMediaQuery";
import { signOutUser } from "../../auth/firebase";

import {
  gestionTabsItems,
  carteraTabsItems,
} from "../../utils/tabs-items/tabItems";

import _ from "lodash";

import {
  TabsContainer,
  TabsTitle,
  TabsLinkContainer,
  TabsLink,
} from "./Tabs.styles";

const Tabs = () => {
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const dispatch = useDispatch();
  const currentModule = useSelector((state) => state.currentModule);
  const user = useSelector((state) => state.user);
  const [tabItems, setTabsItems] = useState([]);
  const [currentTab, setCurrentTab] = useState("");

  const toggleTab = (tab) => {
    if (currentModule === "gestion")
      dispatch(setCurrentGestionTab({ currentGestionTab: tab }));
    if (currentModule === "cartera")
      dispatch(setCurrentCarteraTab({ currentCarteraTab: tab }));
    setCurrentTab(tab);
  };

  const handleSignOut = () => {
    dispatch(signOutUser());
    window.location.href = "/";
  };

  useEffect(() => {
    if (currentModule === "gestion") {
      setTabsItems(gestionTabsItems);
    } else if (currentModule === "cartera") {
      setTabsItems(carteraTabsItems);
    } else {
      setTabsItems([]);
    }
  }, [currentModule]);

  return (
    <TabsContainer isNotAPhone={isNotAPhone}>
      {isNotAPhone && (
        <TabsTitle isNotAPhone={isNotAPhone}>
          <Link to="/" className="title">
            pharma.pa
          </Link>
          <div className="flex gap-5 items-center text-sm">
            <span>{user.email}</span>
            <button
              className="hover:bg-[#112D4E] hover:text-white rounded-md transition-all py-1 px-2"
              onClick={handleSignOut}
            >
              Cerrar sesión
            </button>
          </div>
        </TabsTitle>
      )}
      <TabsLinkContainer isNotAPhone={isNotAPhone}>
        <div className="flex gap-3 overflow-auto no-scrollbar text-sm font-medium">
          {tabItems.map((tab) => (
            <TabsLink
              key={tab}
              to={`${currentModule}/${tab}`}
              className={currentTab === tab ? "active-tabs" : ""}
              onClick={() => toggleTab(tab)}
            >
              {_.startCase(tab)}
            </TabsLink>
          ))}
        </div>
      </TabsLinkContainer>
    </TabsContainer>
  );
};

export default Tabs;
