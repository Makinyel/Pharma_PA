import { endpoints } from "./endpoints";

export const tabEndpoints = {
  purchase: endpoints.buy.base,
  sale: endpoints.sale.base,
  transfer: endpoints.transfer.base,
};

export const detailsEndpoints = {
  purchase: endpoints.purchaseDetails.base,
  sale: endpoints.saleDetails.base,
  transfer: endpoints.transferDetails.base,
};
