import React from "react";
import { useSelector } from "react-redux";
import ProductsContent from "./ProductsContent";

const Products = () => {
  const productsTab = useSelector((state) => state.productsTab);
  return <ProductsContent tab={productsTab} />;
};

export default Products;
