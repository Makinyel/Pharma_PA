import React from "react";
import SelectInput from "./SelectInput";
import TextInput from "./TextInput";

const Input = ({ title, description, type }) => {
  if (type === "select") {
    return <SelectInput title={title} description={description} />;
  } else {
    return <TextInput title={title} description={description} />;
  }
};

export default Input;
