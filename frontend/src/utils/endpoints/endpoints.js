const BASE_URL = "http://localhost:8080";

export const endpoints = {
  user: {
    save: `${BASE_URL}/users`,
    getAll: `${BASE_URL}/users`,
    getUpdateDelete: (id) => `${BASE_URL}/users/${id}`,
    getByEmail: (email) => `${BASE_URL}/users/email/${email}`,
  },
  provider: {
    save: `${BASE_URL}/third-party`,
    getAll: `${BASE_URL}/third-party`,
    getUpdateDelete: (id) => `${BASE_URL}/third-party/${id}`,
  },
  thirdParty: {
    save: `${BASE_URL}/third-party`,
    getAll: `${BASE_URL}/third-party`,
    getUpdateDelete: (id) => `${BASE_URL}/third-party/${id}`,
  },
  client: {
    getAll: `${BASE_URL}/third-party`,
    getUpdateDelete: (id) => `${BASE_URL}/third-party/${id}`,
  },
  product: {
    save: `${BASE_URL}/product`,
    getAll: `${BASE_URL}/product`,
    getByName: (name) => `${BASE_URL}/product/detail?name=${name}`,
    delete: (id) => `${BASE_URL}/${id}`,
  },
  productname: {
    save: `${BASE_URL}/product`,
    getAll: `${BASE_URL}/product`,
    getByName: (name) => `${BASE_URL}/product/detail?name=${name}`,
    delete: (id) => `${BASE_URL}/${id}`,
  },
  sourcewarehouse: {
    save: `${BASE_URL}/warehouse`,
    getAll: `${BASE_URL}/warehouse`,
    getById: (id) => `${BASE_URL}/warehouse/detail?id=${id}`,
    getByName: (name) => `${BASE_URL}/warehouse/name?name=${name}`,
    delete: (id) => `${BASE_URL}/warehouse/delete?id=${id}`,
  },
  destinationwarehouse: {
    save: `${BASE_URL}/warehouse`,
    getAll: `${BASE_URL}/warehouse`,
    getById: (id) => `${BASE_URL}/warehouse/detail?id=${id}`,
    getByName: (name) => `${BASE_URL}/warehouse/name?name=${name}`,
    delete: (id) => `${BASE_URL}/warehouse/delete?id=${id}`,
  },
  warehouse: {
    save: `${BASE_URL}/warehouse`,
    getAll: `${BASE_URL}/warehouse`,
    getById: (id) => `${BASE_URL}/warehouse/detail?id=${id}`,
    getByName: (name) => `${BASE_URL}/warehouse/name?name=${name}`,
    delete: (id) => `${BASE_URL}/warehouse/delete?id=${id}`,
  },
  concentration: {
    save: `${BASE_URL}/concentration`,
    getAll: `${BASE_URL}/concentration`,
    getByName: (name) => `${BASE_URL}/concentration/name?name=${name}`,
  },
  brand: {
    save: `${BASE_URL}/brand`,
    getAll: `${BASE_URL}/brand`,
    getById: (id) => `${BASE_URL}/brand/id/${id}`,
    getByName: (name) => `${BASE_URL}/brand/name?name=${name}`,
  },
  preparation: {
    save: `${BASE_URL}/preparation`,
    getAll: `${BASE_URL}/preparation`,
    getByName: (name) => `${BASE_URL}/preparation/name?name=${name}`,
    delete: (id) => `${BASE_URL}/preparation/${id}`,
  },
  buy: {
    base: `${BASE_URL}/buy`,
    last: `${BASE_URL}/buy/last`,
  },
  purchaseDetails: {
    base: `${BASE_URL}/buy-details`,
    last: `${BASE_URL}/buy/last`,
  },
  sale: {
    base: `${BASE_URL}/sale`,
    last: `${BASE_URL}/sale/last`,
  },
  saleDetails: {
    base: `${BASE_URL}/sale-details`,
    last: `${BASE_URL}/sale-details/last`,
  },
  transfer: {
    base: `${BASE_URL}/transfer`,
    last: `${BASE_URL}/transfer/last`,
  },
  transferDetails: {
    base: `${BASE_URL}/transfer-details`,
    last: `${BASE_URL}/transfer-details/last`,
  },
};
