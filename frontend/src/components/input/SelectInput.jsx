import React, { useEffect, useState } from "react";
import { endpoints } from "../../utils/endpoints/endpoints.js";
import { Container } from "./Input.styles.jsx";
import _ from "lodash";

const SelectInput = ({ title, description }) => {
  let options = [];
  const lowercaseTitle = endpoints[_.lowerCase(title).replace(/\s+/g, "")];
  const [fetchedOptions, setFetchedOptions] = useState([]);

  if (lowercaseTitle) {
    const endpoint = lowercaseTitle.getAll;

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
      options = [{ name: "CLIENT" }, { name: "PROVIDER" }];
    } else if (title === "Document type") {
      options = [{ name: "CEDULA" }, { name: "PASAPORTE" }, { name: "NIT" }];
    }
  }

  return (
    <Container>
      <h3>{title}</h3>
      <p>{description}</p>
      <select name={title.toLowerCase()}>
        <option>Option to select...</option>
        {options.map((option) => (
          <option key={option.id} value={option.name}>
            {option.name}
          </option>
        ))}
      </select>
    </Container>
  );
};

export default SelectInput;
