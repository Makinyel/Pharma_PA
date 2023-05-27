import React, { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { toast } from "sonner";

import Button from "../components/button/Button";
import Input from "../components/input/Input";

import {
  brandFormInfo,
  concentrationFormInfo,
  preparationFormInfo,
  productsForminfo,
  warehouseFormInfo,
} from "../utils/form-info/products.forminfo.js";

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
    product: productsForminfo,
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

    const requestBodyMap = {
      product: {
        name: formData.get("name"),
        description: formData.get("description"),
        buyingPrice: parseFloat(formData.get("buying price")),
        sellingPrice: parseFloat(formData.get("selling price")),
        brand: formData.get("brand"),
        presentation: formData.get("preparation"),
        concentration: formData.get("concentration"),
      },
      brand: { name: formData.get("name") },
      preparation: { name: formData.get("name") },
      warehouse: {
        name: formData.get("name"),
        address: formData.get("address"),
        phone: formData.get("phone"),
      },
      concentration: {
        name: formData.get("name"),
        prefix: formData.get("prefix"),
      },
    };

    const tabEndpoints = {
      product: endpoints.product.save,
      brand: endpoints.brand.save,
      preparation: endpoints.preparation.save,
      warehouse: endpoints.warehouse.save,
      concentration: endpoints.concentration.save,
    };

    const response = await fetch(tabEndpoints[productsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestBodyMap[productsTab]),
    });

    if (!response.ok) {
      toast.error(`There was an error saving the ${productsTab}.`);
      return;
    }

    const saved = await response.json();

    toast.promise(() => Promise.resolve(saved), {
      loading: "Loading...",
      success: (data) => {
        return `${_.upperFirst(productsTab)} has been successfully saved.`;
      },
      error: `There was an error saving the ${productsTab}.`,
    });

    clearFormFields(formRef);
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
