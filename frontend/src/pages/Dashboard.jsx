import React, { useEffect, useState } from "react";
import { Header, Subheader } from "../styles/FormStyles";
import Table from "../components/Table";

import { endpoints } from "../utils/endpoints/endpoints";

const Dashboard = () => {
  const [allProducts, setAllProducts] = useState([]);
  const [allThirdParty, setAllThirdParty] = useState([]);

  const getAllProductsData = async () => {
    await fetch(endpoints.product.getAll)
      .then((res) => res.json())
      .then((data) => setAllProducts(data));
  };
  const getAllPersonasData = async () => {
    await fetch(endpoints.client.getAll)
      .then((res) => res.json())
      .then((data) => setAllThirdParty(data));
  };

  useEffect(() => {
    const getData = async () => {
      await getAllProductsData();
      await getAllPersonasData();
    };
    getData();
  }, []);

  return (
    <div>
      <Header>Dashboard</Header>
      <div className="flex flex-col gap-10">
        <section>
          <Subheader>All products</Subheader>
          <div className="mx-10">
            <Table tableData={allProducts} />
          </div>
        </section>
        <section>
          <Subheader>See your providers</Subheader>
          <div className="mx-10">
            <Table tableData={allThirdParty} />
          </div>
        </section>
      </div>
    </div>
  );
};

export default Dashboard;
