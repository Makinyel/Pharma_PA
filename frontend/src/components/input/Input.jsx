import { useState, useEffect } from "react";
import { Container } from "./Input.styles.jsx";
import { endpoints } from "../../utils/endpoints/endpoints.js";
import _ from "lodash";

const Input = ({ title, description, type }) => {
  const [options, setOptions] = useState([]);

  useEffect(() => {
    if (type === "select") {
      console.log(
        endpoints[_.chain(title).toLower().replace(/\s/g, "").value()]
      );
      const endpoint = endpoints[title.toLowerCase()].getAll;
      fetch(endpoint)
        .then((response) => response.json())
        .then((data) => {
          setOptions(data);
        });
    }
  }, [title, type]);

  return (
    <Container>
      <h3>{title}</h3>
      <p>{description}</p>
      {type === "select" ? (
        <select name={title.toLowerCase()}>
          <option>Option to select...</option>
          {options.length > 0 &&
            options.map((option) => (
              <option key={option.id} value={option.nombre}>
                {option.nombre}
              </option>
            ))}
        </select>
      ) : (
        <input type={type} name={title.toLowerCase()} required />
      )}
    </Container>
  );
};

export default Input;
