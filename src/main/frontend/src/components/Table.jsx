import { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import {
  cxcTableColumns,
  cxpTableColumns,
} from "../utils/table-info/cartera/cartera-table-info";
import MUIDataTable from "mui-datatables";
import axios from "axios";

const Table = ({ title, endpoint }) => {
  const [data, setData] = useState([]);
  const [columns, setColumns] = useState([]);
  const currentCarteraTab = useSelector((state) => state.currentCarteraTab);

  const getMuiTheme = () =>
    createTheme({
      shadows: Array(25).fill("none"),
      components: {
        MuiTableCell: {
          styleOverrides: {
            root: {
              padding: "10px",
              backgroundColor: "white",
            },
          },
        },
      },
    });

  const getData = async () => {
    await axios.get(endpoint).then((res) => {
      const data = res.data;
      setData(data);
    });
  };

  useEffect(() => {
    getData();
    if (currentCarteraTab === "cxp") setColumns(cxpTableColumns);
    if (currentCarteraTab === "cxc") setColumns(cxcTableColumns);
  }, [endpoint, currentCarteraTab]);

  return (
    <ThemeProvider theme={getMuiTheme()}>
      <MUIDataTable columns={columns} data={data} />
    </ThemeProvider>
  );
};

export default Table;
