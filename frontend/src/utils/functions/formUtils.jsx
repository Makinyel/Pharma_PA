export const clearFormFields = (formRef) => {
  if (!formRef || !formRef.current) return;

  const form = formRef.current;
  const inputs = form.querySelectorAll("input, textarea, select");

  inputs.forEach((input) => {
    const type = input.type.toLowerCase();
    switch (type) {
      case "text":
      case "email":
      case "password":
      case "number":
      case "search":
      case "tel":
      case "url":
        input.value = "";
        break;
      case "radio":
      case "checkbox":
        input.checked = false;
        break;
      case "select-one":
      case "select-multiple":
        input.selectedIndex = -1;
        break;
      case "textarea":
        input.value = "";
        break;
      default:
        break;
    }
  });
};
