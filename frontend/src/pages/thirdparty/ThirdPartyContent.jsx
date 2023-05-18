import { React, useEffect, useState, useRef } from "react";
import { useSelector } from "react-redux";
import {
  buyFormInfo,
  sellFormInfo,
  transferFormInfo,
} from "../../utils/form-info/transactionsFormInfo";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  GestionContentWrapper,
  GestionContentHeader,
  GestionContentFormWrapper,
  GestionContentButtonWrapper,
  InputRow,
  InputWrapper,
} from "../products/ProductsContentStyles";

import { useMediaQuery } from "@mui/material";
import { clearFormFields } from "../../utils/functions/formUtils";
import { endpoints } from "../../utils/endpoints/endpoints";

import _ from "lodash";
import { thirdPartyFormInfo } from "../../utils/form-info/thirdPartyFormInfo";

const ThirdPartyContent = ({ tab }) => {
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const marcaName = formData.get("brand");
    const presentacionName = formData.get("preparation");
    const concentracionName = formData.get("concentration");

    const endpoint = endpoints[tab.toLowerCase()].getAll;

    //TODO CHECK THIS
    try {
      fetch(endpoint);
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nombre: formData.get("name"),
          descripcion: formData.get("description"),
          precioCosto: parseFloat(formData.get("buying price")),
          precioVenta: parseFloat(formData.get("selling price")),
          marca: marcaName,
          presentacion: presentacionName,
          concentracion: concentracionName,
        }),
      });

      if (response.ok) {
        alert(`${_.upperFirst(tab)} has been successfully saved.`);
        clearFormFields(formRef);
      } else {
        alert(`There was an error saving the ${tab}.`);
      }
    } catch (error) {
      console.error("Error saving the " + tab, error);
      alert("There was an error saving the product.");
    }
  };

  return (
    <GestionContentWrapper>
      <GestionContentHeader>Add a third party</GestionContentHeader>
      <GestionContentFormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {thirdPartyFormInfo.map(({ title, description, type }, index) => (
              <InputWrapper key={index}>
                <Input title={title} description={description} type={type} />
              </InputWrapper>
            ))}
          </InputRow>
          <GestionContentButtonWrapper isNotAPhone={isNotAPhone}>
            <Button type="submit" text="Save" />
          </GestionContentButtonWrapper>
        </form>
      </GestionContentFormWrapper>
    </GestionContentWrapper>
  );
};

export default ThirdPartyContent;
