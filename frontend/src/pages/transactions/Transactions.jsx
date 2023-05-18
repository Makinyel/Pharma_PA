import React from "react";
import { useSelector } from "react-redux";
import TransactionsContent from "./TransactionsContent.jsx";

const Transactions = () => {
  const transactionsTab = useSelector((state) => state.transactionsTab);
  return <TransactionsContent tab={transactionsTab} />;
};

export default Transactions;
