import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: null,
  isOpen: false,
  productsTab: "product",
  transactionsTab: "buy",
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
    setCurrentModule: (state, action) => {
      state.currentModule = action.payload;
    },
    setActiveModuleStyles: (state, action) => {
      state.activeModuleStyles = action.payload;
    },
  },
});

export const {
  setUser,
  toggleOn,
  toggleOff,
  setProductsTab,
  setTransactionsTab,
  setCurrentModule,
  setActiveModuleStyles,
} = stateSlice.actions;
export default stateSlice.reducer;
