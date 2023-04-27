import useMediaQuery from "@mui/material/useMediaQuery";

import Tabs from "../pages/tabs/Tabs";
import DesktopNav from "./nav/DesktopNav";
import MobileNav from "./nav/MobileNav";

const SideBar = ({ children }) => {
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  return (
    <div className={`h-screen ${isNotAPhone && "flex"}`}>
      {isNotAPhone ? <DesktopNav /> : <MobileNav />}
      {/* <DesktopNav /> */}
      <main className="w-full overflow-auto">
        <Tabs />
        {children}
      </main>
    </div>
  );
};
export default SideBar;
