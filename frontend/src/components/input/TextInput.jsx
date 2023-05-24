import React from "react";
import { Container } from "./Input.styles.jsx";

const TextInput = ({ title, description }) => {
  return (
    <Container>
      <h3>{title}</h3>
      <p>{description}</p>
      <input type="text" name={title.toLowerCase()} required />
    </Container>
  );
};

export default TextInput;
