const BASE_URL = "http://localhost:8080";

export const endpoints = {
  product: {
    save: `${BASE_URL}/product`,
    getAll: `${BASE_URL}/product`,
    getById: (id) => `${BASE_URL}/product/detail?id=${id}`,
    getAllByWarehouse: (idBodega) =>
      `${BASE_URL}/producto/findByBodega/${idBodega}`,
    delete: (id) => `${BASE_URL}/${id}`,
  },
  warehouse: {
    save: `${BASE_URL}/warehouse`,
    getAll: `${BASE_URL}/warehouse`,
    getById: (id) => `${BASE_URL}/warehouse/detail?id=${id}`,
    getByName: (name) => `${BASE_URL}/warehouse/nombre?name=${name}`,
    delete: (id) => `${BASE_URL}/warehouse/delete?id=${id}`,
  },
  concentration: {
    save: `${BASE_URL}/concentration`,
    getAll: `${BASE_URL}/concentration`,
    getByName: (name) => `${BASE_URL}/concentration/nombre?name=${name}`,
  },
  brand: {
    save: `${BASE_URL}/brand`,
    getAll: `${BASE_URL}/brand`,
    getById: (id) => `${BASE_URL}/brand/id/${id}`,
    getByName: (name) => `${BASE_URL}/brand/nombre?name=${name}`,
  },
  preparation: {
    save: `${BASE_URL}/preparation`,
    getAll: `${BASE_URL}/preparation`,
    getByName: (name) => `${BASE_URL}/preparation/nombre?name=${name}`,
    delete: (id) => `${BASE_URL}/preparation/${id}`,
  },
};
