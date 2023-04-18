import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import Table from "../../components/Table";

const CarteraContent = ({ tab }) => {
  const currentCarteraTab = useSelector((state) => state.currentCarteraTab);
  const [endpoint, setEndpoint] = useState("");

  useEffect(() => {
    if (currentCarteraTab === "cxc") {
      setEndpoint("https://fakestoreapi.com/products");
    } else if (currentCarteraTab === "cxp") {
      setEndpoint("https://fakestoreapi.com/users");
    }
  }, [currentCarteraTab]);

  return (
    <div className="h-full">
      <div className="mx-auto w-[90%]">
        <Table title={tab} endpoint={endpoint} />
      </div>
    </div>
  );
};

export default CarteraContent;
