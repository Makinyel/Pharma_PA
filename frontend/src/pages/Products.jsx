import React, { useEffect, useState, useRef } from "react";
import { useSelector } from "react-redux";

import Button from "../components/button/Button";
import Input from "../components/input/Input";

import {
  productsFormInfo,
  brandFormInfo,
  preparationFormInfo,
  warehouseFormInfo,
  concentrationFormInfo,
} from "../utils/form-info/productsFormInfo";

import {
  Wrapper,
  Header,
  FormWrapper,
  ButtonWrapper,
  InputRow,
  InputWrapper,
} from "../styles/FormStyles";

import { useMediaQuery } from "@mui/material";
import { clearFormFields } from "../utils/functions/formUtils";
import { endpoints } from "../utils/endpoints/endpoints";

import _ from "lodash";

const Products = ({ tab }) => {
  const productsTab = useSelector((state) => state.productsTab);
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    product: productsFormInfo,
    brand: brandFormInfo,
    preparation: preparationFormInfo,
    warehouse: warehouseFormInfo,
    concentration: concentrationFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[productsTab] || []);
  }, [productsTab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const marcaName = formData.get("brand");
    const presentacionName = formData.get("preparation");
    const concentracionName = formData.get("concentration");

    const tabEndpoints = {
      product: endpoints.product.save,
      brand: endpoints.brand.save,
      preparation: endpoints.preparation.save,
      warehouse: endpoints.warehouse.save,
      concentration: endpoints.concentration.save,
    };

    const endpoint = tabEndpoints[productsTab];

    try {
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
        alert(`${_.upperFirst(productsTab)} has been successfully saved.`);
        clearFormFields(formRef);
      } else {
        alert(`There was an error saving the ${productsTab}.`);
      }
    } catch (error) {
      console.error("Error saving the " + productsTab, error);
      alert("There was an error saving the product.");
    }
  };

  return (
    <Wrapper>
      <Header>Add a new {productsTab}</Header>
      <FormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {formInfo.map(({ title, description, type }) => (
              <InputWrapper key={title}>
                <Input title={title} description={description} type={type} />
              </InputWrapper>
            ))}
          </InputRow>
          <ButtonWrapper isNotAPhone={isNotAPhone}>
            <Button type="submit" text="Save" />
          </ButtonWrapper>
        </form>
      </FormWrapper>
    </Wrapper>
  );
};

export default Products;
