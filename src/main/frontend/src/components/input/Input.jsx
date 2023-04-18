import { Container } from "./Input.styles.jsx";

const Input = ({ title, description, type }) => {
  return (
    <Container>
      <h3>{title}</h3>
      <p>{description}</p>
      {type === "select" ? (
        <select name={title.toLowerCase()}>
          <option>Option to select...</option>
        </select>
      ) : (
        <input type={type} name={title.toLowerCase()} required />
      )}
    </Container>
  );
};

export default Input;
