import React, { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";

import Button from "../components/button/Button";
import Input from "../components/input/Input";

import {
  brandFormInfo,
  concentrationFormInfo,
  preparationFormInfo,
  productsFormInfo,
  warehouseFormInfo,
} from "../utils/form-info/productsFormInfo";

import {
  ButtonWrapper,
  FormWrapper,
  Header,
  InputRow,
  InputWrapper,
  Wrapper,
} from "../styles/FormStyles";

import { useMediaQuery } from "@mui/material";
import { endpoints } from "../utils/endpoints/endpoints";
import { clearFormFields } from "../utils/functions/formUtils";

import _ from "lodash";

const Products = () => {
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

    const tabEndpoints = {
      product: endpoints.product.save,
      brand: endpoints.brand.save,
      preparation: endpoints.preparation.save,
      warehouse: endpoints.warehouse.save,
      concentration: endpoints.concentration.save,
    };

    await fetch(tabEndpoints[productsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: formData.get("name"),
        descripcion: formData.get("description"),
        precioCompra: parseFloat(formData.get("buying price")),
        previoVenta: parseFloat(formData.get("selling price")),
        marca: formData.get("brand"),
        presentacion: formData.get("preparation"),
        concentracion: formData.get("concentration"),
      }),
    })
      .then(() => {
        alert(`${_.upperFirst(productsTab)} has been successfully saved.`);
        clearFormFields(formRef);
      })
      .catch((error) => {
        console.error("Error saving the " + productsTab, error);
        alert(`There was an error saving the ${productsTab}.`);
      });
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
