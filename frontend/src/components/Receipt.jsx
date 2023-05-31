import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { endpoints } from "../utils/endpoints/endpoints";

const Receipt = () => {
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);

  const endpoint = {
    purchase: endpoints.buy.last,
    sale: endpoints.sale.last,
    transfer: endpoints.transfer.last,
  };

  const persona = {
    purchase: "Provider",
    sale: "Client",
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(endpoint[transactionsTab]);
        const data = await response.json();
        setData(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching data:", error);
        setLoading(false);
      }
    };

    fetchData();
  }, [transactionsTab]);

  const printReceipt = () => {
    window.print();
  };

  return (
    <div className="max-w-2xl mx-auto p-4">
      {loading ? (
        <p>Loading data...</p>
      ) : (
        <div>
          <button
            className="my-4 px-4 bg-blue-400 text-white rounded hover:bg-blue-700 w-full"
            onClick={printReceipt}
          >
            Print
          </button>
          <table className="w-full border-collapse">
            <tbody>
              <tr>
                <th
                  colSpan={2}
                  className="border border-black px-4 py1 font-bold text-xl"
                >
                  Receipt Details
                </th>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">
                  Invoice Code:
                </td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.id}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Date:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.date}
                </td>
              </tr>
              <tr>
                <th
                  colSpan={2}
                  className="border border-black px-4 py1 font-bold text-xl"
                >
                  {persona[transactionsTab]} Details
                </th>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">
                  Document:
                </td>
                <td className="border border-black px-4 py1 text-lg">
                  {persona[transactionsTab] === "purchase"
                    ? data.provider.document
                    : data.client.document}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Name:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {persona[transactionsTab] === "purchase"
                    ? data.provider.name
                    : data.client.name}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">
                  Location:
                </td>
                <td className="border border-black px-4 py1 text-lg">
                  {persona[transactionsTab] === "purchase"
                    ? data.provider.location
                    : data.client.location}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Phone:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {persona[transactionsTab] === "purchase"
                    ? data.provider.phone
                    : data.client.phone}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Email:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {persona[transactionsTab] === "purchase"
                    ? data.provider.email
                    : data.client.email}
                </td>
              </tr>
              <tr>
                <th
                  colSpan={2}
                  className="border border-black px-4 py1 font-bold text-xl"
                >
                  User Details
                </th>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Name:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.user.name}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Email:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.user.email}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">
                  Address:
                </td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.user.address}
                </td>
              </tr>
              <tr>
                <td className="border border-black px-4 py1 text-lg">Phone:</td>
                <td className="border border-black px-4 py1 text-lg">
                  {data.user.phone}
                </td>
              </tr>
            </tbody>
          </table>
          <table className="w-full border-collapse mt-4 text-left">
            <tbody>
              <tr>
                <th colSpan={3} className="border border-black px-4 py1">
                  Subtotal:
                </th>
                <td className="border border-black px-4 py1 text-lg font-bold">
                  $ {data.subtotal}
                </td>
              </tr>
              <tr>
                <th colSpan={3} className="border border-black px-4 py1">
                  Iva:
                </th>
                <td className="border border-black px-4 py1 text-lg font-bold">
                  $ {data.iva}
                </td>
              </tr>
              <tr>
                <th colSpan={3} className="border border-black px-4 py1">
                  Total
                </th>
                <td className="border border-black px-4 py-1 text-lg font-bold">
                  $ {data.total}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Receipt;
