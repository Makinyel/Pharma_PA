import React, { useEffect, useState } from "react";
import { endpoints } from "../../utils/endpoints/endpoints.js";
import { Container } from "./Input.styles.jsx";
import _ from "lodash";

const SelectInput = ({ title, description }) => {
  let options = [];

  if (endpoints[_.lowerCase(title).replace(/\s+/g, "")]) {
    const endpoint = endpoints[_.lowerCase(title).replace(/\s+/g, "")].getAll;
    const [fetchedOptions, setFetchedOptions] = useState([]);

    useEffect(() => {
      const fetchData = async () => {
        const data = await fetch(endpoint).then((res) => res.json());
        setFetchedOptions(data);
      };
      fetchData();
    }, [endpoint]);

    options = fetchedOptions;
  } else {
    if (title === "Third party type") {
      options = [{ nombre: "CLIENT" }, { nombre: "PROVIDER" }];
    } else if (title === "Document type") {
      options = [{ nombre: "CEDULA" }, { nombre: "PASAPORTE" }];
    }
  }

  return (
    <Container>
      <h3>{title}</h3>
      <p>{description}</p>
      <select name={title.toLowerCase()}>
        <option>Option to select...</option>
        {options.map((option) => (
          <option key={option.id} value={option.nombre}>
            {option.nombre}
          </option>
        ))}
      </select>
    </Container>
  );
};

export default SelectInput;
