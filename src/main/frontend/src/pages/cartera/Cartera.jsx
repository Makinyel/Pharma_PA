import { useSelector } from "react-redux";
import CarteraContent from "./CarteraContent";

const Cartera = () => {
  const currentCarteraTab = useSelector((state) => state.currentCarteraTab);
  return <CarteraContent tab={currentCarteraTab} />;
};

export default Cartera;
