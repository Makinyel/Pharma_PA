import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: null,
  isOpen: true,
  transaction: {},
  transactionDetails: [],
  transactionDetailsProducts: [],
  transactionInitiated: false,
  productsTab: "product",
  transactionsTab: "purchase",
  thirdsTab: "persona",
  currentModule: "dashboard",
  activeModuleStyles: "",
};

export const stateSlice = createSlice({
  name: "state",
  initialState,
  reducers: {
    setUser: (state, action) => {
      state.user = action.payload.user;
    },
    toggleOn: (state) => {
      state.isOpen = true;
    },
    toggleOff: (state) => {
      state.isOpen = false;
    },
    setProductsTab: (state, action) => {
      state.productsTab = action.payload.productsTab;
    },
    setTransactionsTab: (state, action) => {
      state.transactionsTab = action.payload.transactionsTab;
    },
    setThirdsTab: (state, action) => {
      state.thirdsTab = action.payload.thirdsTab;
    },
    setCurrentModule: (state, action) => {
      state.currentModule = action.payload;
    },
    setActiveModuleStyles: (state, action) => {
      state.activeModuleStyles = action.payload;
    },
    setTransaction: (state, action) => {
      state.transaction = action.payload.transaction;
    },
    setTransactionDetails: (state, action) => {
      const newDetails = action.payload;
      if (newDetails.length === 0) {
        state.transactionDetails = [];
      } else {
        state.transactionDetails = state.transactionDetails.concat(newDetails);
      }
    },
    setTransactionDetailsProducts: (state, action) => {
      const newProducts = action.payload;
      if (newProducts.length === 0) {
        state.transactionDetailsProducts = []; // Clear the array if payload is empty
      } else {
        state.transactionDetailsProducts =
          state.transactionDetailsProducts.concat(newProducts);
      }
    },
    toggleTransactionInitiated: (state) => {
      state.transactionInitiated = true;
    },
    untoggleTransactionInitiated: (state) => {
      state.transactionInitiated = false;
    },
  },
});

export const {
  setUser,
  toggleOn,
  toggleOff,
  setTransaction,
  setTransactionDetails,
  setTransactionDetailsProducts,
  toggleTransactionInitiated,
  untoggleTransactionInitiated,
  setProductsTab,
  setTransactionsTab,
  setThirdsTab,
  setCurrentModule,
  setActiveModuleStyles,
} = stateSlice.actions;
export default stateSlice.reducer;
