import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: null,
  isOpen: false,
  currentProductosTab: "producto",
  currentMovimientosTab: "compra",
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
    setCurrentProductosTab: (state, action) => {
      state.currentProductosTab = action.payload.currentProductosTab;
    },
    setCurrentMovimientosTab: (state, action) => {
      state.currentMovimientosTab = action.payload.currentMovimientosTab;
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
  setCurrentProductosTab,
  setCurrentMovimientosTab,
  setCurrentModule,
  setActiveModuleStyles,
} = stateSlice.actions;
export default stateSlice.reducer;
