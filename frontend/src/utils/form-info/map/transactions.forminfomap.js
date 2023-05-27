import {
  initPurchaseFormInfo,
  transferFormInfo,
  initSaleFormInfo,
} from "../transactions.forminfo";

import {
  purchaseDetailsFormInfo,
  saleDetailsFormInfo,
  transferDetailsFormInfo,
} from "../transaction-details.forminfo";

export const formInfoMap = {
  purchase: initPurchaseFormInfo,
  transfer: transferFormInfo,
  sale: initSaleFormInfo,
};

export const detailsFormInfoMap = {
  purchase: purchaseDetailsFormInfo,
  transfer: transferDetailsFormInfo,
  sale: saleDetailsFormInfo,
};
