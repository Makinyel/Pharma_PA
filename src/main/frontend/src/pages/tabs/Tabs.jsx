import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  setCurrentModule,
  setCurrentGestionTab,
  setCurrentCarteraTab,
} from "../../state/state";
import { Link } from "react-router-dom";
import useMediaQuery from "@mui/material/useMediaQuery";

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
  const currentModule = useSelector((state) => state.currentModule);
  const [tabItems, setTabsItems] = useState([]);
  const [currentTab, setCurrentTab] = useState("");
  const dispatch = useDispatch();

  const toggleTab = (tab) => {
    if (currentModule === "gestion")
      dispatch(setCurrentGestionTab({ currentGestionTab: tab }));
    if (currentModule === "cartera")
      dispatch(setCurrentCarteraTab({ currentCarteraTab: tab }));
    setCurrentTab(tab);
  };

  useEffect(() => {
    if (currentModule === "gestion") {
      setTabsItems(gestionTabsItems);
    } else if (currentModule === "cartera") {
      setTabsItems(carteraTabsItems);
    }
  });

  return (
    <TabsContainer isNotAPhone={isNotAPhone}>
      {isNotAPhone && (
        <TabsTitle isNotAPhone={isNotAPhone}>
          <Link to="/" className="title" onClick={() => dispatch("dashboard")}>
            pharma.pa
          </Link>
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
    //   {isNotAPhone && (
    //     <div className="px-5">
    //       <Link to="/" className="title" onClick={() => dispatch("dashboard")}>
    //         pharma.pa
    //       </Link>
    //     </div>
    //   )}
    //   <div className={`border-b border-dark ${isNotAPhone && "pt-3"}`}>
    //     <div className="flex gap-3 overflow-auto no-scrollbar text-sm font-medium">
    //       {tabItems.map((tab) => (
    //         <Link
    //           key={tab}
    //           to={`${currentModule}/${tab}`}
    //           className={currentTab === tab ? "tabs active-tabs" : "tabs"}
    //           onClick={() => toggleTab(tab)}
    //         >
    //           {_.startCase(tab)}
    //         </Link>
    //       ))}
    //     </div>
    //   </div>
    // </div>
  );
};

export default Tabs;
