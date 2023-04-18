import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isOpen: false,
  currentGestionTab: "medicamentos",
  currentCarteraTab: "cxc",
  currentModule: "dashboard",
  activeModuleStyles: "",
};

export const stateSlice = createSlice({
  name: "state",
  initialState,
  reducers: {
    toggleOn: (state) => {
      state.isOpen = true;
    },
    toggleOff: (state) => {
      state.isOpen = false;
    },
    setCurrentGestionTab: (state, action) => {
      state.currentGestionTab = action.payload.currentGestionTab;
    },
    setCurrentCarteraTab: (state, action) => {
      state.currentCarteraTab = action.payload.currentCarteraTab;
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
  toggleOn,
  toggleOff,
  setCurrentGestionTab,
  setCurrentCarteraTab,
  setCurrentModule,
  setActiveModuleStyles,
} = stateSlice.actions;
export default stateSlice.reducer;
