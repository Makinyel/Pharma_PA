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

const TransactionsContent = ({ tab }) => {
  const [formInfo, setFormInfo] = useState([]);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    buy: buyFormInfo,
    transfer: transferFormInfo,
    sell: sellFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[tab] || []);
  }, [tab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const user = useSelector((state) => state.user);

    const productName = formData.get("product");
    const sourceWarehouseName = formData.get("warehouse");
    const destinationWarehouseName = formData.get("warehouse");

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
          cantidad: parseInt(formData.get("quantity")),
          producto: formData.get("product"),
          bodegaOrigen: formData.get("buying price"),
          bodegaDestino: formData.get("selling price"),
          user: user.id,
        }),
      });

      if (response.ok) {
        alert(`${_.upperFirst(tab)} has been successfully made.`);
        clearFormFields(formRef);
      }
    } catch (error) {
      console.error("Error saving the " + tab, error);
      alert(`There was an error processing the ${tab}.`);
    }
  };

  return (
    <GestionContentWrapper>
      <GestionContentHeader>{_.upperFirst(tab)} a product</GestionContentHeader>
      <GestionContentFormWrapper>
        <form ref={formRef} onSubmit={handleFormSubmit}>
          <InputRow>
            {formInfo.map(({ title, description, type }, index) => (
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

export default TransactionsContent;
