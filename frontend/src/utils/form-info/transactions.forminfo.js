export const initPurchaseFormInfo = [
  {
    type: "text",
    title: "Invoice code",
    description: "Invoice's code for the purchase you want to register.",
  },
  {
    type: "select",
    title: "Provider",
    description: "Name of the provider who you will buy from.",
  },
];

export const initSaleFormInfo = [
  {
    type: "select",
    title: "Client",
    description: "Who you will be buying from",
  },
];

export const transferFormInfo = [
  {
    type: "date",
    title: "Product",
    description: "Product to transfer",
  },
  {
    type: "text",
    title: "Quantity",
    description: "Quantity to transfer",
  },
  {
    type: "select",
    title: "Source Warehouse",
    description: "Source warehouse",
  },
  {
    type: "select",
    title: "Destination Warehouse",
    description: "Destination warehouse",
  },
];
