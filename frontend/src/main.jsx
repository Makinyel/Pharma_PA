import React from "react";
import ReactDOM from "react-dom/client";
import stateReducer from "./state/state";
import { Provider } from "react-redux";
import App from "./App";
import "./index.css";

import { configureStore } from "@reduxjs/toolkit";
const store = configureStore({
  reducer: stateReducer,
});

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
);
