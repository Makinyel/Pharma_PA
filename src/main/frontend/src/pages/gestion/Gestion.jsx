import { useSelector } from "react-redux";
import GestionContent from "./GestionContent";

const Gestion = () => {
  const currentGestionTab = useSelector((state) => state.currentGestionTab);
  return <GestionContent tab={currentGestionTab} />;
};

export default Gestion;
