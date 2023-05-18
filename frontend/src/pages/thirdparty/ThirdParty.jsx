import React from "react";
import { useSelector } from "react-redux";
import ThirdPartyContent from "./ThirdPartyContent";

const ThirdParty = () => {
  const thirdsTab = useSelector((state) => state.thirdsTab);
  return <ThirdPartyContent tab={thirdsTab} />;
};

export default ThirdParty;
