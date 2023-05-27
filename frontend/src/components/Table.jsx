import { createTheme, ThemeProvider } from "@mui/material/styles";
import MUIDataTable from "mui-datatables";

const Table = ({ tableData }) => {
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

  const options = {
    selectableRows: "none",
    download: false,
    print: false,
    viewColumns: true,
    responsive: "standard",
    onTableChange: () => {},
    columns: [0, 3], // Indices of the columns to be selected by default
  };

  const generateColumns = (data) => {
    if (!data || data.length === 0) {
      return [];
    }

    const keys = Object.keys(data[0]);

    const columns = keys.map((key) => {
      const capitalizedLabel = key.charAt(0).toUpperCase() + key.slice(1);

      return {
        name: key,
        label: capitalizedLabel,
        options: {
          filter: false,
          sort: false,
          customBodyRender: (data) => {
            if (typeof data === "object") {
              return JSON.stringify(data);
            }
            return data;
          },
        },
      };
    });

    return columns;
  };

  const columns = generateColumns(tableData);

  return (
    <ThemeProvider theme={getMuiTheme()}>
      <MUIDataTable columns={columns} data={tableData} options={options} />
    </ThemeProvider>
  );
};

export default Table;
